function toSubmit(){
	console.log("toSubmit started");
	window.location = "http://localhost:8080/project-1/employee/reimb_submit.html";
}
function toView(){
	console.log("toView started");
	window.location = "http://localhost:8080/project-1/employee/view_reimbursements.html";
	
	
}
function submitReimbursement(){
	console.log("submitReimbursement started.");
	let fd = new FormData();
	let submitForm = document.submitForm;
	let amount = submitForm.amount.value;
	let description = submitForm.description.value;
	let reimb_type = submitForm.reimb_type.value;
	fd.append("amount", submitForm.amount.value);
	fd.append("description", submitForm.description.value);
	fd.append("reimb_type", submitForm.reimb_type.value);
	fd.append("receipt", submitForm.receipt.value);

	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			alert("Reimbursement Submitted");
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("There was an error submitting your reimbursement");
		}
		
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/submit_reimb");
	xhr.send(JSON.stringify(fd));
}