(ns sicp-solution.utils
  (:use [clojure.test]))

(defn square [n] (* n n))

(defmacro compare-time
  [expr1 expr2]
  `(let [start1# (. System (nanoTime))
         ret1# ~expr1
         end1# (. System(nanoTime))
         start2# (. System (nanoTime))
         ret2# ~expr2
         end2# (. System(nanoTime))]
     (println (str "Elapsed time for first expression: " (/ (double (- end1# start1#)) 1000000.0) " msecs"))
     (println (str "Elapsed time for second expression: " (/ (double (- end2# start2#)) 1000000.0) " msecs"))
     ret1#))

(defn float= [x y]
  (<= (Math/abs (- x y)) 0.00001))

;tests
(deftest floats-equal-check-test
  (is true? (float= 0.3 0.3000001))
  (is true? (float= 0.00000000867 0.0000000086999))

  (is false? (float= 0.389 0.344))
  (is false? (float= 0.00089 0.00024)))