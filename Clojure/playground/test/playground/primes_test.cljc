(ns playground.primes-test
  (:require [clojure.test :refer :all]
            [playground.primes :refer :all]))

(deftest test-sieve
  (testing "Test sieving"
    (is (= (sieve (cons 1 (repeatedly 100 #(rand-int 100000)))) [1]))
    (is (= (sieve [2 4 5 6 8 9 10 14]) [2 5 9]))))

(deftest test-20-primes
  (testing "Incorrect for the primes under 20."
    (is (= '(2 3 5 7 11 13 17 19) (primes-until 20)))))
