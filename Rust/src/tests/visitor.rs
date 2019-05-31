trait Visitor<'a, T> {
    fn visit_leaf(&mut self, value: &'a T);
    fn visit_inner(
        &mut self,
        value: &'a T,
        left: &'a impl Node<Elem = T>,
        right: &'a impl Node<Elem = T>,
    );
}

trait Node {
    type Elem;
    fn accept<'a>(&'a self, visitor: &mut impl Visitor<'a, Self::Elem>);
}

enum BorrowNode<'a, T> {
    Leaf(T),
    Inner(T, &'a BorrowNode<'a, T>, &'a BorrowNode<'a, T>),
}
impl<'n, T> Node for BorrowNode<'n, T> {
    type Elem = T;
    fn accept<'a>(&'a self, visitor: &mut impl Visitor<'a, Self::Elem>) {
        match *self {
            BorrowNode::Leaf(ref v) => visitor.visit_leaf(v),
            BorrowNode::Inner(ref value, left, right) => visitor.visit_inner(value, left, right),
        }
    }
}

enum BoxNode<T> {
    Leaf(T),
    Inner(T, Box<BoxNode<T>>, Box<BoxNode<T>>),
}
impl<T> Node for BoxNode<T> {
    type Elem = T;
    fn accept<'a>(&'a self, visitor: &mut impl Visitor<'a, Self::Elem>) {
        match *self {
            BoxNode::Leaf(ref v) => visitor.visit_leaf(v),
            BoxNode::Inner(ref value, ref left, ref right) => {
                visitor.visit_inner(value, &**left, &**right)
            }
        }
    }
}

// There may be `RcNode`, `ArcNode`, etc.

/// The inorder traversal of a node.
fn inorder<'a, N: Node>(root: &'a N) -> Vec<&'a N::Elem> {
    struct InorderVisitor<'a, T> {
        list: Vec<&'a T>,
    }
    impl<'a, T> Visitor<'a, T> for InorderVisitor<'a, T> {
        fn visit_leaf(&mut self, value: &'a T) {
            self.list.push(value)
        }
        fn visit_inner(
            &mut self,
            value: &'a T,
            left: &'a impl Node<Elem = T>,
            right: &'a impl Node<Elem = T>,
        ) {
            left.accept(self);
            self.list.push(value);
            right.accept(self);
        }
    }
    let mut visitor = InorderVisitor { list: Vec::new() };
    root.accept(&mut visitor);
    visitor.list
}

#[test]
fn tests() {
    assert_eq!(
        vec![2, 1, 4, 3, 5],
        inorder(&BorrowNode::Inner(
            3,
            &BorrowNode::Inner(1, &BorrowNode::Leaf(2), &BorrowNode::Leaf(4)),
            &BorrowNode::Leaf(5)
        ))
        .into_iter()
        .map(|x| *x)
        .collect::<Vec<_>>()
    );
    assert_eq!(
        vec![2, 1, 4, 3, 5],
        inorder(&BoxNode::Inner(
            3,
            Box::new(BoxNode::Inner(
                1,
                Box::new(BoxNode::Leaf(2)),
                Box::new(BoxNode::Leaf(4))
            )),
            Box::new(BoxNode::Leaf(5))
        ))
        .into_iter()
        .map(|x| *x)
        .collect::<Vec<_>>()
    );
}
