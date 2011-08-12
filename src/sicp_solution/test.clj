(ns sicp-solution.test)

(defn cartesian-product
  "All the ways to take one item from each sequence"
  [& seqs]
  (let [v-original-seqs (vec seqs)
	step
	(fn step [v-seqs]
	  (let [increment
		(fn [v-seqs]
		  (loop [i (dec (count v-seqs)), v-seqs v-seqs]
		    (if (= i -1) nil
			(if-let [rst (next (v-seqs i))]
			  (assoc v-seqs i rst)
			  (recur (dec i) (assoc v-seqs i (v-original-seqs i)))))))]
	    (when v-seqs
	       (cons (map first v-seqs)
		     (lazy-seq (step (increment v-seqs)))))))]
    (when (every? first seqs)
      (lazy-seq (step v-original-seqs)))))


(defn selections
  "All the ways of taking n (possibly the same) elements from the sequence of items"
  [items n]
  (apply cartesian-product (take n (repeat items))))

(defn age-combinations
  [age]
  (distinct
   (map #(sort (vec %)) (selections (range 1 age) 3))))

(defn product-equals?
  [lst prod]
  (= (reduce * lst) prod))

(defn print-results
  [lst]
  (println (str (vec lst) " sum is: " (reduce + lst))))

(defn print-ages-with-product
  [prod]
  (doseq [ages (age-combinations prod)]
    (if (product-equals? ages prod)
      (print-results ages))))