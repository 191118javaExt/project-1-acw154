function toReview(){
	console.log("toSubmit started");
	window.location = "http://localhost:8080/project-1/fm/fm_review_reimb.html";
}
function toView(){
	console.log("toView started");
	window.location = "http://localhost:8080/project-1/fm/view_reimbursements.html";
}
function reviewReimbursement(){
	console.log("ReviewReimbursement started");
	let reviewForm = document.reviewForm;
	let reimb_id = reviewForm.reimb_id.value;
	let reimb_status = reviewForm.reimb_status.value;
	
	let reviewTemplate = {
			reimb_id: reimb_id,
			reimb_status: reimb_status
	};
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			alert("Reimbursement " + reimb_id + "has been " + reimb_status);
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("There was an error reviewing the reimbursement");
		}
		
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/review_reimb");
	xhr.send(JSON.stringify(reviewTemplate));
}