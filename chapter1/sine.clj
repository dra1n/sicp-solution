(ns chapter1.sine
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs)]))

(def call-count (atom 0))

(defn cube [x] (* x x x))

(defn p [x] (- (* 3 x) (* 4 (cube x))))

(defn sine [angle]
  (swap! call-count inc)
  (if (not (> (abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))
