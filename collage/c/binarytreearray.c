#include <stdio.h>
#include <stdlib.h>

#define MAX 15 // Define the maximum number of nodes in the binary tree

// Function to add an element to the tree
void addNode(int tree[], int value, int index) {
    if (index >= MAX) {
        printf("No space to insert node\n");
        return;
    }
    
    // If the node is empty, insert the value
    if (tree[index] == -1) {
        tree[index] = value;
        printf("Inserted %d at index %d\n", value, index);
    } else {
        // Try to insert in left or right subtree
        if (value < tree[index])
            addNode(tree, value, 2 * index + 1);  // Insert in left child
        else
            addNode(tree, value, 2 * index + 2);  // Insert in right child
    }
}

// Function to perform in-order traversal
void inOrderTraversal(int tree[], int index) {
    if (index >= MAX || tree[index] == -1) {
        return;
    }
    
    // Left -> Root -> Right
    inOrderTraversal(tree, 2 * index + 1);   // Left child
    printf("%d ", tree[index]);              // Root
    inOrderTraversal(tree, 2 * index + 2);   // Right child
}

int main() {
    int tree[MAX];
    int value, choice;

    // Initialize the tree with -1 indicating empty nodes
    for (int i = 0; i < MAX; i++) {
        tree[i] = -1;
    }

    // Keep asking the user to add nodes
    do {
        printf("Enter a value to add to the binary tree: ");
        scanf("%d", &value);
        addNode(tree, value, 0);

        printf("Do you want to add another node? (1 for Yes / 0 for No): ");
        scanf("%d", &choice);
    } while (choice != 0);

    // Perform in-order traversal
    printf("In-order traversal of the binary tree: ");
    inOrderTraversal(tree, 0);
    printf("\n");

    return 0;
}
