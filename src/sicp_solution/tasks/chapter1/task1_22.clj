(ns sicp-solution.tasks.chapter1.task1-22
  (:use [sicp-solution.tasks.chapter1.task1-21 :only (smallest-divisor)])
  (:use [clojure.test]))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn report-prime [elapsed-time]
  (println " *** ")
  (println (/ elapsed-time 1.0e9)))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (do
      (println n)
      (report-prime (- (System/nanoTime) start-time)))))

(defn timed-prime-test [n]
;  (println n)
  (start-prime-test n (System/nanoTime)))

(defn search-for-primes
  [lst]
  (doseq [number lst]
    (timed-prime-test number)))

;evaluations
;(search-for-primes (range 1000 1020))
;(search-for-primes (range 10000 10038))
;(search-for-primes (range 100000 100045))
;(search-for-primes (range 1000000 1000038))

; results in seconds
; 1009
;  *** 
; 1.14958E-4
; 1013
;  *** 
; 3.8063E-5
; 1019
;  *** 
; 1.3758E-5

; 10007
;  *** 
; 1.57492E-4
; 10009
;  *** 
; 4.43073E-4
; 10037
;  *** 
; 4.3148E-4

; 100003
;  *** 
; 2.17136E-4
; 100019
;  *** 
; 9.4984E-5
; 100043
;  *** 
; 9.7638E-5

; 1000003
;  *** 
; 3.63454E-4
; 1000033
;  *** 
; 2.43956E-4
; 1000037
;  *** 
; 2.52336E-4

; and more

; 9999991
;  *** 
; 2.29079E-4
; 10000019
;  *** 
; 2.30407E-4


; clojure benchmarks

; (time (doseq [number (range 1000 1100)] (prime? number)))
; "Elapsed time: 0.633111 msecs"

; (time (doseq [number (range 10000 10100)] (prime? number)))
; "Elapsed time: 0.6732 msecs"

; (time (doseq [number (range 100000 100100)] (prime? number)))
; "Elapsed time: 0.776286 msecs"

; (time (doseq [number (range 1000000 1000100)] (prime? number)))
; "Elapsed time: 1.115016 msecs"

;test
(deftest prime-test
  (is (true? (prime? 3)))
  (is (true? (prime? 17)))
  (is (true? (prime? 199)))

  (is (false? (prime? 10)))
  (is (false? (prime? 19999))))