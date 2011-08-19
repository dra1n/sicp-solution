(ns sicp-solution.tasks.chapter1.task1-23
  (:use [sicp-solution.utils :only (square compare-time)])
  (:use [clojure.test]))

(def test-primes [1009 1013 1019 10007 10009 10037 100003 100019 100043 1000003 1000037 1000039 9999999900000001 1111111111111111111])

(defn nxt [n] (if (< n 3) 3 (+ n 2)))

(defn divides?
  [a b]
  (= (rem b a) 0))

(defn find-divisor
  [n test-divisor inc-rule]
  (loop [n n, test-divisor test-divisor]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          true (recur n (inc-rule test-divisor)))))

(defn smallest-divisor
  [n inc-rule] (find-divisor n 2 inc-rule))

(defn old-prime? [n]
  (= n (smallest-divisor n inc)))

(defn prime? [n]
  (= n (smallest-divisor n nxt)))

; lets roll
; (doseq [n test-primes]
;   (println n)
;   (compare-time (prime? n) (old-prime? n))
;   (println))

; 1009
; Elapsed time for first expression: 0.252057 msecs
; Elapsed time for second expression: 0.080736 msecs

; 1013
; Elapsed time for first expression: 0.016413 msecs
; Elapsed time for second expression: 0.022139 msecs

; 1019
; Elapsed time for first expression: 0.015505 msecs
; Elapsed time for second expression: 0.02172 msecs

; 10007
; Elapsed time for first expression: 0.037086 msecs
; Elapsed time for second expression: 0.064882 msecs

; 10009
; Elapsed time for first expression: 0.036248 msecs
; Elapsed time for second expression: 0.064743 msecs

; 10037
; Elapsed time for first expression: 0.036667 msecs
; Elapsed time for second expression: 3.135245 msecs

; 100003
; Elapsed time for first expression: 0.117822 msecs
; Elapsed time for second expression: 0.163358 msecs

; 100019
; Elapsed time for first expression: 0.107207 msecs
; Elapsed time for second expression: 0.16294 msecs

; 100043
; Elapsed time for first expression: 0.106228 msecs
; Elapsed time for second expression: 0.161962 msecs

; 1000003
; Elapsed time for first expression: 0.327905 msecs
; Elapsed time for second expression: 0.53701 msecs

; 1000037
; Elapsed time for first expression: 0.308908 msecs
; Elapsed time for second expression: 0.517734 msecs

; 1000039
; Elapsed time for first expression: 0.320851 msecs
; Elapsed time for second expression: 0.517384 msecs

; 9999999900000001
; Elapsed time for first expression: 6645.763193 msecs
; Elapsed time for second expression: 11668.595777 msecs

; 1111111111111111111
; Elapsed time for first expression: 62893.476822 msecs
; Elapsed time for second expression: 112996.814468 msecs

;tests
(deftest nxt-test
  (is (= (nxt 1) 3))
  (is (= (nxt 2) 3))
  (is (= (nxt 4) 6)))

(deftest prime-test
  (is (true? (prime? 3)))
  (is (true? (prime? 17)))
  (is (true? (prime? 199)))

  (is (false? (prime? 10)))
  (is (false? (prime? 19999))))