class Point {
    constructor(public readonly x: number,
                public readonly y: number) {
    }
    static new({x, y}: {
        x: number,
        y: number,
    }): Point { return new Point(x, y) }
    add(rhs: Point): Point {
        return new Point(this.x+rhs.x, this.y+rhs.y)
    }
}

console.log(new Point(1, 3));
console.log(Point.new({ x: 1, y: 3}));
let point_add = Point.prototype.add;
console.log(point_add.call(new Point(1, 2), new Point(3, 4)))