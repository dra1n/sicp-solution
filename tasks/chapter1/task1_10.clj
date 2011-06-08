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

(defn f [n] (ackermann 0 n))
(defn g [n] (ackermann 1 n))
(defn h [n] (ackermann 2 n))
(defn k [n] (* 5 n n))

; tests

(deftest ackermann-test
  (is (= (expt 2 10) (ackermann 1 10)))
  (is (= (expt 2 (expt 2 (expt 2 2))) (ackermann 2 4)))
  (is (= (expt 2 (expt 2 (expt 2 2))) (ackermann 3 3))))
