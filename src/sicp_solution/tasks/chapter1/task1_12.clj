(ns sicp-solution.tasks.chapter1.task1-12
  (:use [clojure.test]))

(defn pascals-triangle [row col]
  (if (or (= col 1) (= col row))
    1
    (+ (pascals-triangle
         (- row 1) (- col 1))
       (pascals-triangle
         (- row 1) col))))

;tests

(deftest pascals-triangle-test
  (is (= 1 (pascals-triangle 1 1)))
  (is (= 1 (pascals-triangle 4 4)))
  (is (= 2 (pascals-triangle 3 2)))
  (is (= 4 (pascals-triangle 5 4))))
