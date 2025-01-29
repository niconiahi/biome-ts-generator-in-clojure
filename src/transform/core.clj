(ns transform.core
  (:require [clojure.data.json :refer [read-str]]
            [clojure.string :refer [join split]]))
(declare make-type)

(defn parse-json
  []
  (-> "src/transform/schema-full.json"
      slurp
      (read-str :key-fn keyword)
      identity))

(defn get-definition [key schema] (get-in schema [:definitions (keyword key)]))

(defn parse-array
  [definition]
  (str (join "" (vals (get definition :items))) "[]"))

(defn get-definition-name [ref] (last (split ref #"/")))

(defn parse-object
  [definition n]
  (prn "definition is" n)
  (prn "name is" n)
  (let [properties (get definition :properties)]
    (join
     "\n"
     (concat
      [(join " " ["type" n "=" "{"])]
      (reduce
       (fn [accumulator [n property]]
         (let [type (get property :type)
               any-of (get property :anyOf)]
              ; (prn "current accumulator is" accumulator)
              ; (prn "property type is" type)
              ; (prn "property anyof is" any-of)
              ; (prn "is string" (string? type))
              ; (prn "is vector" (vector? type))
              ; (prn "is anyof" (vector? any-of))
           (cond
             (string? type) (conj accumulator (str "  " (name n) ": " type))
             (vector? type) (conj accumulator
                                  (str "  " (name n) ": " (join " | " type)))
             (vector? any-of)
             (conj accumulator
                   (str "  " (name n)
                        ": " (join
                              " | "
                              (map
                               (fn [any-of-property]
                                 (let [type (get any-of-property :type)
                                       ref (get any-of-property :$ref)]
                                   (cond (string? type) type
                                         (string? ref)
                                         (get-definition-name ref))))
                               any-of)))))))
       []
       properties)
      ["}"]))))

(defn parse-string
  [definition name]
  (let [enum (get definition :enum)
        quoted (map #(str "\"" % "\"") enum)]
    (join (concat "type " name " = " (join " | " quoted)))))

(defn make-type
  [definition name]
  (let [type (get definition :type)]
    (cond (= type "array") (parse-array definition)
          (= type "object") (parse-object definition name)
          (= type "string") (parse-string definition name)
          (= type "integer") "number")))
