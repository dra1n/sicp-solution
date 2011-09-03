(ns sicp-solution.tasks.chapter1.task1-29
  (:use [sicp-solution.utils :only (compare-time square cube float=)])
  (:use [clojure.test]))

(defn sum
  [term a nxt b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (nxt a) nxt b))))

(defn integral
  [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum f (+ a (/ dx 2)) add-dx b)
     dx))

(defn simpson-integral
  [f a b n]
  (let [h (/ (- b a) n)
        sum-of-boundary-values (+ (f a) (f b))
        doubled-f #(* 2 (f %))
        quadruple-f #(* 4 (f %))
        nxt #(+ % (* 2 h))]
    (* (/ h 3)
       (+ sum-of-boundary-values
          (sum doubled-f (+ a (* 2 h)) nxt (- b h))
          (sum quadruple-f (+ a h) nxt (- b (* 2 h)))))))

;evaluation time comparison

;(compare-time (simpson-integral cube 0 1 10000) (integral cube 0 1 0.01))

;Elapsed time for first expression: 74.045098 msecs
;Elapsed time for second expression: 0.585968 msecs

;tests
(deftest simpson-integral-test
  (is (float= (float (simpson-integral #(* 2 (square %)) 1 2 1000))
              (float 14/3)
              0.01))
  (is (float= (float (simpson-integral #(+ 8 (* 2 %) (- (square %))) -2 4 1000))
              36)))

(deftest compare-evaluation-methods
  (is (float= (float (simpson-integral cube 0 1 1000))
              (float (integral cube 0 1 0.01))
              0.01))
  (is (float= (float (simpson-integral cube 0 1 10000))
              (float (integral cube 0 1 0.01))
              0.001)))