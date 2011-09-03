(ns sicp-solution.utils
  (:use [clojure.test]))

(defn square [n] (* n n))

(defn cube [n] (* n n n))

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

(defn- scale [x y]
  (if (or (zero? x) (zero? y))
    1
    (Math/abs x)))

(defn float=
  ([x y] (float= x y 0.00001))
  ([x y epsilon] (<= (Math/abs (- x y))
                     (* (scale x y) epsilon))))

(defn float<
  ([x y] (float< x y 0.00001))
  ([x y epsilon] (< x
                    (- y (* (scale x y) epsilon)))) )

(defn float>
  ([x y] (float< y x))
  ([x y epsilon] (float< y x epsilon)))

(defn float<=
  ([x y] (not (float> x y)))
  ([x y epsilon] (not (float> x y epsilon))))

(defn float>=
  ([x y] (not (float< x y)))
  ([x y epsilon] (not (float< x y epsilon))))

;tests
(deftest floats-compare-test
  (is (false? (float< 12.3049 12.305)))
  (is (true? (float< 12.3049 12.305 1e-6)))
  (is (true? (float<= 12.305 12.3049)))
  (is (false? (float<= 12.305 12.3049 1e-6)))
  (is (true? (float> 12.305 12.3049 1e-6))))