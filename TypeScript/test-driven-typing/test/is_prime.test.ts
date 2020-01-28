import { is_prime } from "../src/index"
import * as assert from "assert"

for (let num of [2.7, 3.6, 4.5]) {
    test(num + ` should not be prime`, () => {
        assert.equal(is_prime(num), false)
    })
}

for (let num of [-1, 0, 1]) {
    test(num + ` should not be prime`, () => {
        assert.equal(is_prime(num), false)
    })
}

for (let num of [2, 3, 5, 7, 11]) {
    test(num + ` should be prime`, () => {
        assert.equal(is_prime(num), true)
    })
}

for (let num of [4, 6, 8, 9]) {
    test(num + ` should not be prime`, () => {
        assert.equal(is_prime(num), false)
    })
}
