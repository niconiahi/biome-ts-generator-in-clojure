
(ns transform.test
  (:require [transform.core :refer [parse-json get-definition make-type]]))

(def name-1 "StringSet")
(assert (some? (get-definition name-1 (parse-json))))
(assert (= "string[]" (make-type (get-definition name-1 (parse-json)) name-1)))
; (println (make-type (get-definition name-1 (parse-json)) name-1))

(def name-2 "Actions")
(assert (= "type Actions = {
  source: Source | null,
}"
           (make-type (get-definition name-2 (parse-json)) name-2)))
; (println (make-type (get-definition name-2 (parse-json)) name-2))

(def name-3 "RulePlainConfiguration")
(assert
 (= "type RulePlainConfiguration = \"warn\" | \"error\" | \"info\" | \"off\""
    (make-type (get-definition name-3 (parse-json)) name-3)))
; (println (make-type (get-definition name-3 (parse-json)) name-3))

(def name-4 "FixKind")
(assert (= "type FixKind = {
}"
           (make-type (get-definition name-3 (parse-json)) name-3)))
; (println (make-type (get-definition name-3 (parse-json)) name-3))

(def name-5 "RuleWithFixNoOptions")
(assert
 (=
  "type RuleWithFixNoOptions = {
  fix: FixKind | null, 
  level: RulePlainConfiguration,
}"
  (make-type (get-definition name-3 (parse-json)) name-3)))
; (println (make-type (get-definition name-3 (parse-json)) name-3))
