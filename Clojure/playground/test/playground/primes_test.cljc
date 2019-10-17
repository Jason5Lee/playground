(ns playground.primes-test
  (:require [clojure.test :refer :all]
            [playground.primes :refer :all]))

(deftest test-simple
  (testing "Incorrect for the primes under 20."
    (is (= '(2 3 5 7 11 13 17 19) (primes-until 20)))))
