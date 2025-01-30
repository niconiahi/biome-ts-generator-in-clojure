(ns transform.core
  (:require [clojure.data.json :refer [read-str]]
            [clojure.string :refer [join split]]))
(declare make-type)

(defn parse-json
  []
  (-> "src/transform/schema.json"
      slurp
      (read-str :key-fn keyword)
      identity))

(defn get-definition [schema key] (get-in schema [:definitions (keyword key)]))

(defn parse-array
  [definition]
  (str (join "" (vals (get definition :items))) "[]"))

(defn get-definition-name [ref] (last (split ref #"/")))

(defn parse-string
  [definition name]
  (let [enum (get definition :enum)
        quoted (map #(str "\"" % "\"") enum)]
    (join (concat "type " name " = " (join " | " quoted)))))

(defn parse-one-of
  [definition name]
  (let [one-of (get definition :oneOf)]
    (->> (map #(first (get % :enum)) one-of)
         (map #(str "\"" % "\""))
         (join " | ")
         (str "type " name " = "))))

(defn parse-object
  [definition n]
  (let [properties (get definition :properties)]
    (join
     "\n"
     (concat
      [(join " " ["type" n "=" "{"])]
      (reduce
       (fn [accumulator [n property]]
         (let [type (get property :type)
               any-of (get property :anyOf)
               all-of (get property :allOf)]
              ; (prn "current accumulator is" accumulator)
              ; (prn "property name is" n)
              ; (prn "all-of is" all-of)
              ; (prn "property type is" type)
              ; (prn "property anyof is" any-of)
              ; (prn "is string" (string? type))
              ; (prn "is vector" (vector? type))
              ; (prn "is anyof" (vector? any-of))
           (cond
             (string? type) (conj accumulator
                                  (str "  " (name n) ": " type ","))
             (vector? type) (conj
                             accumulator
                             (str "  " (name n) ": " (join " | " type) ","))
             (vector? all-of)
             (let [ref (get (first all-of) :$ref)]
               (conj
                accumulator
                (str "  " (name n) ": " (get-definition-name ref) ",")))
             (vector? any-of)
             (conj accumulator
                   (str "  "
                        (name n)
                        ": "
                        (join " | "
                              (map (fn [any-of-property]
                                     (let [type (get any-of-property :type)
                                           ref (get any-of-property :$ref)]
                                            ; (prn "type is " type)
                                            ; (prn "ref is " ref)
                                       (cond (string? type) type
                                             (string? ref)
                                             (get-definition-name ref))))
                                   any-of))
                        ",")))))
       []
       properties)
      ["}"]))))

(defn make-type
  [definition name]
  (let [type (get definition :type)
        one-of (get definition :oneOf)]
    ; (prn "type is " type)
    ; (prn "one-of is " one-of)
    (cond (= type "array") (parse-array definition)
          (= type "object") (parse-object definition name)
          (= type "string") (parse-string definition name)
          (= type "integer") "number"
          (some? one-of) (parse-one-of definition name))))
