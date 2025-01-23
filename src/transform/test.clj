
(ns transform.test
  (:require [transform.core :refer [parse-json, get-definition, make-type]]
            [clojure.pprint :as pp]))

(assert (some? (parse-json)))
(pp/pprint (get-definition "StringSet" (parse-json)))

(assert (some? (get-definition "StringSet" (parse-json))))
(pp/pprint (get-definition "StringSet" (parse-json)))

(pp/pprint (make-type (get-definition "StringSet" (parse-json))))
(assert (= "string[]" (make-type (get-definition "StringSet" (parse-json)))))
