export const cartData = [
    {
      description: 'Add single item to cart and verify quantity',
      quantity: 1,
      expectedQuantity: 1
    },
    {
      description: 'Add multiple items to cart and verify quantity',
      quantity: 3,
      expectedQuantity: 3
    },
    {
      description: 'Update item quantity in cart to 5',
      quantity: 5,
      expectedQuantity: 5
    },
    {
      description: 'Update item quantity in cart to 0 (remove item)',
      quantity: 0,
      expectedQuantity: 0
    }
  ];
  