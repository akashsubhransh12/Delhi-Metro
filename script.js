// Handle "Make Payment" click
document.getElementById("pay-button").addEventListener("click", function() {
  const name = document.getElementById("name").value;
  const mobile = document.getElementById("mobile").value;

  if (name && mobile) {
    alert("Payment successful for " + name + ". Ticket generated.");
  } else {
    alert("Please enter all required information.");
  }
});

// Handle "Print Ticket" click
document.getElementById("print-ticket").addEventListener("click", function() {
  const name = document.getElementById("name").value;
  const mobile = document.getElementById("mobile").value;

  if (name && mobile) {
    const printContent = `
      <h2>Delhi Metro Ticket</h2>
      <p>Name: ${name}</p>
      <p>Mobile No: ${mobile}</p>
      <p>Thank you for using Delhi Metro!</p>
    `;
    const printWindow = window.open("", "", "width=400,height=600");
    printWindow.document.write(printContent);
    printWindow.document.close(); // Ensure the document is properly closed before printing
    printWindow.print();
  } else {
    alert("Please enter all required information to print the ticket.");
  }
});

// Handle "Save Ticket" click (for local storage)
document.getElementById("save-ticket").addEventListener("click", function() {
  const name = document.getElementById("name").value;
  const mobile = document.getElementById("mobile").value;

  if (name && mobile) {
    const ticketData = { name, mobile, date: new Date().toLocaleString() };
    localStorage.setItem("savedTicket", JSON.stringify(ticketData));
    alert("Ticket saved locally. Check browser storage for saved ticket.");
  } else {
    alert("Please enter all required information to save the ticket.");
  }
});

// Handle Route Calculation
document.getElementById("calculate-route").addEventListener("click", function() {
  const startStation = document.getElementById("start-station").value;
  const endStation = document.getElementById("end-station").value;

  if (startStation && endStation) {
    // Simulated route calculation (to be replaced with actual algorithm)
    const distance = calculateDistance(startStation, endStation); // Calculate distance based on selected stations
    const cost = calculateCost(distance); // Calculate cost based on distance

    // Display the results in the designated section
    const resultSection = document.getElementById("result-section");
    resultSection.innerHTML = `
      <h3>Route Details</h3>
      <p>Route from ${startStation} to ${endStation}</p>
      <p>Distance: ${distance} km</p>
      <p>Cost: ${cost}</p>
    `;
  } else {
    alert("Please select both start and end stations to calculate the route.");
  }
});

// Function to calculate distance based on stations (simulated)
function calculateDistance(startStation, endStation) {
  // Example distances, you can modify this with actual logic
  const distances = {
    "Station A": { "Station B": 5, "Station C": 10 },
    "Station B": { "Station A": 5, "Station C": 8 },
    "Station C": { "Station A": 10, "Station B": 8 },
  };

  return distances[startStation][endStation] || "unknown"; // Return calculated distance
}

// Function to calculate cost based on distance
function calculateCost(distance) {
  if (distance === "unknown") return "unknown"; // Handle unknown distance case
  // Example cost calculation (e.g., ₹6 per km)
  const costPerKm = 6; // Cost per kilometer
  const totalCost = costPerKm * distance; // Calculate total cost
  return `₹${totalCost}`; // Return formatted cost
}
