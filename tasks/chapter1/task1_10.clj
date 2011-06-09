(ns task1_10
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs expt)]))

(defn ackermann [x y]
  (cond
    (= y 0) 0
    (= x 0) (* 2 y)
    (= y 1) 2
    true (ackermann
      (- x 1)
      (ackermann x (- y 1)))))


; ackerman partial functions
(def f (partial ackermann 0))
(def g (partial ackermann 1))
(def h (partial ackermann 2))

; generalizations of A(0, n), A(1, n) and A(2, n)
(defn f-simplification [n] (* 2 n))

(defn g-simplification [n]
  (if (= 0 n)
    0
    (expt 2 n)))

(defn h-simplification [n]
  (if (= 0 n)
    0
    (loop [counter n, result 2]
      (if (= counter 1)
        result
        (recur (dec counter) (expt 2 result))))))

; tests
(deftest ackermann-test
  (is (= (expt 2 10) (ackermann 1 10)))
  (is (= (expt 2 (expt 2 (expt 2 2))) (ackermann 2 4)))
  (is (= (expt 2 (expt 2 (expt 2 2))) (ackermann 3 3))))

(deftest f-simplification-test
  (is (= (f 0) (f-simplification 0)))
  (is (= (f 1) (f-simplification 1)))
  (is (= (f 5) (f-simplification 5))))

(deftest g-simplification-test
  (is (= (g 0) (g-simplification 0)))
  (is (= (g 1) (g-simplification 1)))
  (is (= (g 5) (g-simplification 5))))

(deftest h-simplification-test
  (is (= (h 0) (h-simplification 0)))
  (is (= (h 1) (h-simplification 1)))
  (is (= (h 3) (h-simplification 3))))
