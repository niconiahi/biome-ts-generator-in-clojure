
(ns transform.test
  (:require [transform.core :refer [parse-json get-definition make-type]]))

(def name-1 "StringSet")
; (println (make-type (get-definition name-1 (parse-json)) name-1))
(assert (= "string[]" (make-type (get-definition name-1 (parse-json)) name-1)))

(def name-2 "Actions")
; (println (make-type (get-definition name-2 (parse-json)) name-2))
(assert (= "type Actions = {
  source: Source | null,
}"
           (make-type (get-definition name-2 (parse-json)) name-2)))

(def name-3 "RulePlainConfiguration")
; (println (make-type (get-definition name-3 (parse-json)) name-3))
(assert
 (= "type RulePlainConfiguration = \"warn\" | \"error\" | \"info\" | \"off\""
    (make-type (get-definition name-3 (parse-json)) name-3)))

(def name-4 "FixKind")
; (println (make-type (get-definition name-4 (parse-json)) name-4))
(assert (= "type FixKind = \"none\" | \"safe\" | \"unsafe\""
           (make-type (get-definition name-4 (parse-json)) name-4)))

; (def name-5 "RuleWithFixNoOptions")
; (println (make-type (get-definition name-5 (parse-json)) name-5))
; (assert
;  (=
;   "type RuleWithFixNoOptions = {
;   fix: FixKind | null,
;   level: RulePlainConfiguration,
; }"
;   (make-type (get-definition name-5 (parse-json)) name-5)))
