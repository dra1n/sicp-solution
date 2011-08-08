(ns sicp-solution.chapter1.sqrt-iter
  (:use [clojure.contrib.math :only (abs)]))

(defn square [x] (* x x))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt [number]
  (loop [guess 1.0 x number]
    (if (good-enough? guess x)
      guess
      (recur (improve guess x) x))))
