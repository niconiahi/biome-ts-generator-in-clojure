
(ns transform.test
  (:require [transform.core :refer [parse-json, get-definition, make-type, parse-object]]
            [clojure.pprint :as pp]))

(assert (some? (parse-json)))
; (pp/pprint (parse-json))

(assert (some? (get-definition "StringSet" (parse-json))))
; (pp/pprint (get-definition "StringSet" (parse-json)))

(assert (= "string[]" (make-type (get-definition "StringSet" (parse-json)))))

(assert (= "type AssistsConfiguration = {
  enabled: boolean | null
  prioritize: boolean
  ignore: string[] | null
  include: string[] | null
}" (parse-object (get-definition "AssistsConfiguration" (parse-json)) "AssistsConfiguration")))
; (println (parse-object (get-definition "AssistsConfiguration" (parse-json)) "AssistsConfiguration"))
