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
(defn always-thing [& param] 100)
#_:clj-kondo/ignore
(defn make-thingy [x] (fn [& param] x))
(defn triplicate [f] (f) (f) (f))

