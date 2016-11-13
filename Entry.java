    public final class Entry {
         int     mDegree = 0;       // Number of children
         boolean mIsMarked = false; // Whether this node is marked

         Entry mNext;   // Next and previous elements in the list
         Entry mPrev;

         Entry mParent; // Parent in the tree, if any.

         Entry mChild;  // Child node, if any.

         int mPriority; // Its priority

        public int getPriority() {
            return mPriority;
        }

         Entry(int priority) {
            mNext = mPrev = this;
            mPriority = priority;
        }
    }