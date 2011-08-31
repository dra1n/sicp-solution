(ns sicp-solution.tasks.chapter1.task1-07
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs)]))

(defn good-enough? [guess old-guess]
  (< (abs (- guess old-guess)) 0.0001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt [number]
  (loop [guess 1.0, old-guess 2.0, x number]
    (if (good-enough? guess old-guess)
      guess
      (recur (improve guess x) guess x))))


; tests
(deftest sqrt-test
  (is (> 0.0001 (abs (- (sqrt 9) 3)))))
