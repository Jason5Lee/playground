/**
 * Parsing `E -> E+E | E-E | (E) | N`
 * which can be
 * ```
 * E -> (E)E’ | NE’
 * E’ -> +EE’ | -EE’ | epsilon
 * ```
 */
function parseE(str: string, ptr: number): [{}, number] {
    console.assert(ptr < str.length);
    if (str[ptr] == '(') {
        let [inside, ptrR] = parseE(str, ptr+1);
        console.assert(str[ptrR] == ')'); ++ptrR;
        let [after, ptrF] = parseEprime(str, ptrR);
        return [{
            type: 'bracket',
            inside,
            after,
        }, ptrF]
    } else {
        let [after, ptrF] = parseEprime(str, ptr + 1);
        return [{
            type: 'char',
            char: str[ptr],
            after,
        }, ptrF]
    }
}
function parseEprime(str: string, ptr: number): [{}, number] {
    if (str[ptr] == '+') {
        let [rhs, ptrR] = parseE(str, ptr+1);
        let [after, ptrF] = parseEprime(str, ptrR);
        return [{
            type: 'add',
            rhs,
            after,
        }, ptrF]
    } else if (str[ptr] == '-') {
        let [rhs, ptrR] = parseE(str, ptr+1);
        let [after, ptrF] = parseEprime(str, ptrR);
        return [{
            type: 'sub',
            rhs,
            after,
        }, ptrF]
    } else return [{
        type: 'epsilon'
    }, ptr]
}

import util = require('util');

console.log(util.inspect(parseE("(1+2-3+4-5)+((2-3)+(1-2)-3)", 0), {depth: null}))
