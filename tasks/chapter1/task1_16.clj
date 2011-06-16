(ns tasks.chapter1.task1_16
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs expt)]))

(defn square [x] (* x x))

(defn fast-expt
  [number power]
  (loop [a1 1, a2 1, b number, n power]
    (cond
      (= 0 n)
        a2
      (even? n)
        (recur (* (square a1) b) a2 b (/ n 2))
      true
        (recur a1 (* a2 a1 b) b (- n 1)))))

; tests
(deftest fast-expt-test
  (is (= (expt 1 0) (fast-expt 1 0)))
  (is (= (expt 0 1) (fast-expt 0 1)))
  (is (= (expt -2 3) (fast-expt -2 3)))

  (is (= (expt 2 6) (fast-expt 2 6)))
  (is (= (expt 2 4) (fast-expt 2 4)))

  (is (= (expt 4 5) (fast-expt 4 5)))
  (is (= (expt 4 25) (fast-expt 4 25))))
