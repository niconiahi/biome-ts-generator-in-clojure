(ns functions.test
  (:require [functions.core :refer [greeting, do-nothing, always-thing, make-thingy, triplicate, opposite]]))

(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))

(assert (= "Hello" (do-nothing "Hello")))
(assert (= "Peanut" (do-nothing "Peanut")))

(assert (= 100 (always-thing "Hello")))
(assert (= 100 (always-thing "Peanut")))

(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))

(let [s (atom "")]
  (assert (= "ABABAB" (triplicate #(swap! s str "AB")))))

(assert (= false ((opposite identity) true)))
