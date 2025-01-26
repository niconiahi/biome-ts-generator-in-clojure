(ns transform.core
  (:require [clojure.data.json :refer [read-str]]
            [clojure.string :refer [join]]))

(defn parse-json []
  (-> "src/transform/schema.json"
      slurp
      (read-str :key-fn keyword)
      identity))

(defn get-definition [key schema]
  (get-in schema [:definitions (keyword key)]))

(defn parse-array [definition]
  (str (join "" (vals (get definition :items))) "[]"))

(defn parse-object [definition]
  (let
   [properties (get definition :properties)]
    (reduce
     (fn [accumulator [n, property]]
       (let [type (get property :type)
             any-of (get property :anyOf)]
         (prn "name is" n)
         (prn "current accumulator is" accumulator)
         (prn "property type is" type)
         (prn "property anyof is" any-of)
         (prn "is string" (string? type))
         (prn "is vector" (vector? type))
         (prn "is anyof" (vector? any-of))
         (cond
           (string? type) (conj accumulator (str (name n) ": " type "\n"))
           (vector? type) (conj accumulator (str (name n) ": " (join " | " type) "\n"))
           (vector? any-of) (println "i'm a any-of structure that needs to be parsed"))))
     []
     properties)))

(defn make-type [definition]
  (let [type (get definition :type)]
    (cond
      (= type "array") (parse-array definition)
      (= type "object") (parse-object definition))))
