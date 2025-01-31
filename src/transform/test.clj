
(ns transform.test
  (:require [transform.core :refer [parse-json get-definition make-type]]))

(def name-1 "StringSet")
; (println (make-type (get-definition name-1 (parse-json)) name-1))
(assert (= "string[]" (make-type (get-definition (parse-json) name-1) name-1)))

(def name-2 "Actions")
; (println (make-type (get-definition (parse-json) name-2) name-2))
(assert (= "type Actions = {
  source: Source | null,
}"
           (make-type (get-definition (parse-json) name-2) name-2)))

(def name-3 "RulePlainConfiguration")
; (println "RulePlainConfiguration"
;          (make-type (get-definition (parse-json) name-3) name-3))
(assert
 (= "type RulePlainConfiguration = \"warn\" | \"error\" | \"info\" | \"off\""
    (make-type (get-definition (parse-json) name-3) name-3)))

(def name-4 "FixKind")
; (println (make-type (get-definition name-4 (parse-json)) name-4))
(assert (= "type FixKind = \"none\" | \"safe\" | \"unsafe\""
           (make-type (get-definition (parse-json) name-4) name-4)))

(def name-5 "RuleWithFixNoOptions")
; (println (make-type (get-definition (parse-json) name-5) name-5))
(assert
 (=
  "type RuleWithFixNoOptions = {
  fix: FixKind | null,
  level: RulePlainConfiguration,
}"
  (make-type (get-definition (parse-json) name-5) name-5)))

(def name-6 "SuggestedExtensionMapping")
; (println (make-type (get-definition (parse-json) name-6) name-6))
(assert
 (=
  "type SuggestedExtensionMapping = {
  component: string,
  module: string,
}"
  (make-type (get-definition (parse-json) name-6) name-6)))

(def name-7 "A11y")
; (println (make-type (get-definition (parse-json) name-7) name-7))
(assert
 (=
  "type A11y = {
  useValidAnchor: RuleConfiguration | null,
  useKeyWithClickEvents: RuleConfiguration | null,
  useAriaPropsForRole: RuleConfiguration | null,
  useValidLang: RuleConfiguration | null,
  useAltText: RuleConfiguration | null,
  noNoninteractiveTabindex: RuleFixConfiguration | null,
  useAriaActivedescendantWithTabindex: RuleFixConfiguration | null,
  noSvgWithoutTitle: RuleConfiguration | null,
  noPositiveTabindex: RuleFixConfiguration | null,
  useGenericFontNames: RuleConfiguration | null,
  useValidAriaRole: ValidAriaRoleConfiguration | null,
  noHeaderScope: RuleFixConfiguration | null,
  noAriaHiddenOnFocusable: RuleFixConfiguration | null,
  useButtonType: RuleConfiguration | null,
  useValidAriaValues: RuleConfiguration | null,
  useMediaCaption: RuleConfiguration | null,
  useSemanticElements: RuleConfiguration | null,
  useValidAriaProps: RuleFixConfiguration | null,
  useIframeTitle: RuleConfiguration | null,
  all: boolean | null,
  noAccessKey: RuleFixConfiguration | null,
  noAriaUnsupportedElements: RuleFixConfiguration | null,
  useFocusableInteractive: RuleConfiguration | null,
  noAutofocus: RuleFixConfiguration | null,
  useKeyWithMouseEvents: RuleConfiguration | null,
  noRedundantAlt: RuleConfiguration | null,
  noLabelWithoutControl: NoLabelWithoutControlConfiguration | null,
  noInteractiveElementToNoninteractiveRole: RuleFixConfiguration | null,
  useHeadingContent: RuleConfiguration | null,
  useAnchorContent: RuleFixConfiguration | null,
  noRedundantRoles: RuleFixConfiguration | null,
  noBlankTarget: AllowDomainConfiguration | null,
  noDistractingElements: RuleFixConfiguration | null,
  recommended: boolean | null,
  noNoninteractiveElementToInteractiveRole: RuleFixConfiguration | null,
  useHtmlLang: RuleConfiguration | null,
}"
  (make-type (get-definition (parse-json) name-7) name-7)))

