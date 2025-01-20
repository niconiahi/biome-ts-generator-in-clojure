(ns functions.core)

(defn greeting
  ([] (greeting "World"))
  ([greeter] (let [message (str "Hello, " greeter "!")]
               (println message)
               message))
  ([greet greeter] (let [message (str greet ", " greeter "!")]
                     (println message)
                     message)))
(defn do-nothing [x] x)
#_:clj-kondo/ignore
(defn always-thing [& args] 100)
#_:clj-kondo/ignore
(defn make-thingy [x] (fn [& args] x))
(defn triplicate [f] (f) (f) (f))
(defn opposite [f] (fn [& args] (not (apply f args))))
(defn triplicate2 [f & args]
  (triplicate #(apply f args)))
