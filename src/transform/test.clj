
(ns transform.test
  (:require [transform.core :refer [parse-json, get-definition, make-type, parse-object]]
            [clojure.pprint :as pp]))

(assert (some? (parse-json)))
; (pp/pprint (parse-json))

(assert (some? (get-definition "StringSet" (parse-json))))
; (pp/pprint (get-definition "StringSet" (parse-json)))

(assert (= "string[]" (make-type (get-definition "StringSet" (parse-json)))))
; (pp/pprint (make-type (get-definition "StringSet" (parse-json))))

; (pp/pprint (parse-object {:properties {:type "string"}}))
(println (parse-object (get-definition "AssistsConfiguration" (parse-json)) "AssistsConfiguration"))

; (assert (= "type AssistConfiration = {
;   enabled: boolean
; }" (parse-object {:AssistConfiguration
;                   {:type "object",
;                    :properties
;                    {:enabled
;                     {:description "Whether Biome should enable assists via LSP.",
;                      :type "boolean"}},
;                    :additionalProperties false}})))

; (pp/pprint (parse-object {:type "object",
;                           :properties
;                           {:enabled
;                            {:description "Whether Biome should enable assists via LSP.",
;                             :type "boolean"}},
;                           :additionalProperties false}))
;
; (pp/pprint (parse-object {:type "object",
;                           :properties
;                           {:include
;                            {:description
;                             "A list of Unix shell style patterns. The formatter will include files/folders that will match these patterns.",
;                             :anyOf [{:$ref "#/definitions/StringSet"} {:type "null"}]}},
;                           :additionalProperties false}))
;
; (pp/pprint (parse-object {:type "object",
;                           :properties
;                           {:enabled
;                            {:description "Whether Biome should enable assists via LSP.",
;                             :type "boolean"},
;                            :ignore
;                            {:description "Whether Biome should enable assists via LSP.",
;                             :type ["boolean", "string"]},
;                            :include
;                            {:description
;                             "A list of Unix shell style patterns. The formatter will include files/folders that will match these patterns.",
;                             :anyOf [{:$ref "#/definitions/StringSet"} {:type "null"}]}},
;                           :additionalProperties false}))
