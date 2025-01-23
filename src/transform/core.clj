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

(defn make-type [definition] (let [type (get definition :type)]
                               (cond
                                 (= type "array") (str (join "" (vals (get definition :items))) "[]")
                                 (= type "number") (str "(" ")[]"))))
