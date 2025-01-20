(ns functions.core)
(defn greet [] (println "Hello"))
(defn greeting 
  ([] (greeting "World"))
  ([greeter] (
              let [result (str "Hello, " greeter "!")]
              (println result)
              result))
  ([greet greeter] (
              let [result (str greet ", " greeter "!")]
              (println result)
              result))
)

