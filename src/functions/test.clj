(ns functions.test
  (:require [functions.core :refer [greeting, do-nothing, always-thing, make-thingy, triplicate, opposite, triplicate2, hypotenuse, http-get]]
            [clojure.math :refer [cos PI]]))

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

(assert (= false ((opposite identity) true)))

(let [s (atom "")]
  (assert (= "ABABAB" (triplicate2 swap! s str "AB"))))

(assert (= (cos PI) -1.0))
(assert (= (cos PI) -1.0))

(assert (= (hypotenuse 2) 1.0))

(assert (.contains (http-get "https://www.w3.org") "html"))
