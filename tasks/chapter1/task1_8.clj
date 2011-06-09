(ns tasks.chapter1.task1_8
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs)]))

(defn square [x] (* x x))

(defn good-enough? [guess old-guess]
  (< (abs (- guess old-guess)) 0.0001))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn cube-root [number]
  (loop [guess 1.0, old-guess 2.0, x number]
    (if (good-enough? guess old-guess)
      guess
      (recur (improve guess x) guess x))))


; tests
(deftest cube-root-test
  (is (> 0.0001 (abs (- (cube-root 27) 3))))
  (is (> 0.0001 (abs (- (cube-root 8) 2)))))
