use std::{cell::RefCell, fmt, rc::Rc};

type BTreeNodeRef<T> = Rc<RefCell<BTreeNode<T>>>;

#[derive(Clone, Debug)]
pub struct BTreeNode<T> {
    pub data: T,
    pub left: Option<BTreeNodeRef<T>>,
    pub right: Option<BTreeNodeRef<T>>,
}

pub enum Position {
    Top,
    Left,
    Right,
}

impl<T: fmt::Display> fmt::Display for BTreeNode<T> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        self.display_with_indent(f, 0, Position::Top)
    }
}

pub enum ChildNode<T> {
    Leaf(T),
    Left(T, BTreeNodeRef<T>),
    Right(T, BTreeNodeRef<T>),
    Both(T, BTreeNodeRef<T>, BTreeNodeRef<T>),
}

impl<T: fmt::Display> BTreeNode<T> {
    pub fn new(data: T, left: Option<BTreeNodeRef<T>>, right: Option<BTreeNodeRef<T>>) -> Self {
        Self { data, left, right }
    }

    pub fn new_child(node: ChildNode<T>) -> BTreeNodeRef<T> {
        let node = match node {
            ChildNode::Leaf(data) => BTreeNode::new(data, None, None),
            ChildNode::Left(data, left) => BTreeNode::new(data, Some(left), None),
            ChildNode::Right(data, right) => BTreeNode::new(data, None, Some(right)),
            ChildNode::Both(data, left, right) => BTreeNode::new(data, Some(left), Some(right)),
        };
        Rc::new(RefCell::new(node))
    }

    /// Display the tree with indentation
    /// ```
    /// item
    /// └── left
    /// └── right
    ///     └── left
    /// ```
    fn display_with_indent(
        &self,
        f: &mut fmt::Formatter<'_>,
        indent: usize,
        position: Position,
    ) -> fmt::Result {
        match position {
            Position::Top => (),
            _ => {
                for _ in 0..indent - 1 {
                    write!(f, "    ")?;
                }
                write!(f, "└── ")?
            }
        };

        write!(f, "{}", self.data)?;

        if let Some(ref left) = self.left {
            write!(f, "\n")?;
            left.borrow()
                .display_with_indent(f, indent + 1, Position::Left)?;
        }

        if let Some(ref right) = self.right {
            write!(f, "\n")?;
            right
                .borrow()
                .display_with_indent(f, indent + 1, Position::Right)?;
        }

        Ok(())
    }
}
