package com.css.ds.practice.ds.linkedlist;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 After getting her PhD, Christie has become a celebrity at her university,
 and her facebook profile is full of friend requests. Being the nice girl she is,
 Christie has accepted all the requests.

 Now Kuldeep is jealous of all the attention she is getting from other guys,
 so he asks her to delete some of the guys from her friend list.

 To avoid a 'scene', Christie decides to remove some friends from her friend list,
 since she knows the popularity of each of the friend she has, she uses the following algorithm to delete a friend.

 Algorithm Delete(Friend):
 DeleteFriend=false
 for i = 1 to Friend.length-1
 if (Friend[i].popularity < Friend[i+1].popularity)
 delete i th friend
 DeleteFriend=true
 break
 if(DeleteFriend == false)
 delete the last friend

 Input:
 First line contains T number of test cases. First line of each test case contains N,
 the number of friends Christie currently has and K ,the number of friends Christie decides to delete.
 Next lines contains popularity of her friends separated by space.

 Output:
 For each test case print N-K numbers which represent popularity of Christie friend's after deleting K friends.

 Constraints
 1<=T<=1000
 1<=N<=100000
 0<=K< N
 0<=popularity_of_friend<=100

 NOTE:
 Order of friends after deleting exactly K friends should be maintained as given in input.

 SAMPLE INPUT
 3
 3 1
 3 100 1
 5 2
 19 12 3 4 17
 5 3
 23 45 11 77 18

 SAMPLE OUTPUT
 100 1
 19 12 17
 77 18

 * @author Kishore Routhu on 8/7/17 10:38 PM.
 */
public class RemoveFriends {

    public static void main(String[] args) throws IOException {
        IOProvider iop = IOProvider.getConsoleInstance();
        LinkedList list = new LinkedList();

        int t = iop.readInt();
        while (t > 0) {
            int n = iop.readInt();
            int k = iop.readInt();

            for (int i = 0; i < n; i++)
                list.append(iop.readInt());

            list.delete(k);
            list.printList(iop);
            iop.newLine();
            list.clear();

            t--;
        }

        iop.finish();
    }

    private static class LinkedList {

        Node head;
        Node tail;

        private class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
            }
        }

        private boolean isEmpty() {
            return head == null;
        }

        public void append(int data) {

            Node new_node = new Node(data);

            if (isEmpty()) {
                head = new_node;
                tail = new_node;
                return;
            }

            tail.next = new_node;
            tail = new_node;

        }

        public void printList(IOProvider iop) {
            Node current = head;
            while (current != null) {
                iop.printf("%d ", current.data);
                current =  current.next;
            }
        }

        public void delete(int k) {
            Node current = head;
            Node previous = null;

            while (k > 0) {
                if (current.data < current.next.data) {
                    if (previous == null) {
                        head = current.next;
                    } else {
                        previous.next = current.next;
                        current = previous;
                    }
                    k--;
                    continue;
                }
                previous = current;
                current = current.next;
            }


            while (k > 0) {
                if (previous != null)
                    previous.next = null;
                else
                    head = null;

                k--;
                previous = null;
            }
        }


        public void clear() {
            head = null;
            tail = null;
        }
    }
}
