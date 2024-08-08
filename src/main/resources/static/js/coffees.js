document.addEventListener('DOMContentLoaded', () => {
    fetch('/coffee/all')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('API response:', data);
            displayCoffees(data.content);
        })
        .catch(error => console.error('Error:', error));
});

function displayCoffees(coffees) {
    console.log('Displaying coffees:', coffees);
    const coffeeList = document.getElementById('coffee-list');
    coffeeList.innerHTML = '';

    coffees.forEach(coffee => {
        const listItem = document.createElement('li');
        listItem.textContent = `${coffee.name} - ${coffee.country} - ${coffee.price} USD - ${coffee.gridType}`;
        coffeeList.appendChild(listItem);
    });
}
