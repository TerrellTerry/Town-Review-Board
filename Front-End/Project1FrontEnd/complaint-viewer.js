const complaintTableBody = document.getElementById("complaintTableBody");
const complaintTableHead = document.getElementById("complaintTableHead");

const updateAllDiv = document.getElementById("updateDiv");

const appUserJSON = localStorage.getItem("appUser"); //Gets the user from local storage. Will be undefined if not found

async function getComplaints(){
    const response = await fetch("http://localhost:8080/complaints");
    const complaints = await response.json();
    return complaints;
}

async function renderComplaintsTable(complaints){
    const testComplaints = await getComplaints();
    const priorities = ["HIGH", "LOW", "IGNORED", "UNASSIGNED", "ADDRESSED"]
    const appUser = JSON.parse(appUserJSON);
    console.log(testComplaints);
    let count = 0;
    // if(appUser !== null){
    //     if(appUser.role === "COUNCIL"){
    //         const updateAllSelector = document.createElement("select");
    //         updateAllSelector.name = "priorities";
    //         updateAllSelector.id = "updateAllPriority";
    
    //         for(const p of priorities){
    //             const temp = document.createElement("option");
    //             temp.value = p;
    //             temp.text = p;
    
    //             updateAllSelector.add(temp);
    //         }
    
    //         const updateAllMeetingInput = document.createElement("input");
    //         updateAllMeetingInput.type = "text";
    //         updateAllMeetingInput.id = "updateAllMeetingId";
    
    //         const updateAllBtn = document.createElement("button");
    //         updateAllBtn.id = "updateAll";
    //         updateAllBtn.innerText = "Update All";
    
    //         updateAllDiv.appendChild(updateAllMeetingInput);
    //         updateAllDiv.appendChild(updateAllSelector);
    //         updateAllDiv.appendChild(updateAllBtn);
    //     }
    // }

    for(const complaint of testComplaints){
        const complaintRow = document.createElement("tr");

        const complaintIdData = document.createElement("td");
        complaintIdData.innerText = complaint.id;

        const complaintSubjectData = document.createElement("td");
        complaintSubjectData.innerText = complaint.subject;
        
        const complaintDescData = document.createElement("td");
        complaintDescData.innerText = complaint.description;

        const complaintMeetingTD = document.createElement("td");

        const complaintPrioritySelect = document.createElement("select");
        
        const complaintPriorityTD = document.createElement("td");

        let updateButton = null;

        if(appUser !== null){
            if(appUser.role === "COUNCIL"){
                let tempPriority = "";
                complaintPrioritySelect.name = "priorities";
                complaintPrioritySelect.id = "priorityId" + complaint.id;
                for(const p of priorities){
                    if(p === complaint.priority){
                        //Add this option first.
                        const temp = document.createElement("option");
                        temp.value = p;
                        tempPriority = p;
                        temp.text = p;

                        complaintPrioritySelect.add(temp);
                        console.log("Found assigned priority" + temp.value);
                        break;
                    }
                }
                for(const p of priorities){

                        if(p !== tempPriority)
                        {
                            const temp = document.createElement("option");
                            temp.value = p;
                            temp.text = p;
                            console.log("Assigning priorities");
                            complaintPrioritySelect.add(temp);
                        }
                    }
                complaintPriorityTD.appendChild(complaintPrioritySelect);

                const complaintMeetingIdData = document.createElement("input");
                if(complaint.meetingId === -1)
                {
                    complaintMeetingIdData.value = "NONE";
                }
                else{
                    complaintMeetingIdData.value = complaint.meetingId;
                }
                complaintMeetingIdData.id = "complaintMeeting" + complaint.id;
                complaintMeetingTD.appendChild(complaintMeetingIdData);
                console.log(appUser + "council");

                if(count < 1){
                    const updateTH = document.createElement("th");
                    updateTH.innerText = "Update";
                    complaintTableHead.appendChild(updateTH);
                }
                count++;

                updateButton = document.createElement("button");
                updateButton.innerText = "Update";
                updateButton.id = "updateComplaint";
                updateButton.dataset.complaintID = complaint.id;
            }
            else if(appUser.role === "CONSTITUENT")
            {
                complaintPriorityTD.innerText = complaint.priority;

                if(complaint.meetingId === -1){
                    complaintMeetingTD.innerText = "NONE"; 
                }
                else{
                    complaintMeetingTD.innerText = complaint.meetingId; 
                }
                
                console.log(appUser + "constituent");
                updateButton = null;
            }
        }
        else{
            complaintPriorityTD.innerText = complaint.priority;

            if(complaint.meetingId === -1){
                complaintMeetingTD.innerText = "NONE"; 
            }
            else{
                complaintMeetingTD.innerText = complaint.meetingId; 
            }
            console.log(appUser + "null");
            updateButton = null;
        }
        
        complaintRow.appendChild(complaintIdData);
        complaintRow.appendChild(complaintSubjectData);
        complaintRow.appendChild(complaintDescData);
        complaintRow.appendChild(complaintMeetingTD);
        complaintRow.appendChild(complaintPriorityTD);
        if(updateButton !== null)
        {
            complaintRow.appendChild(updateButton);
        }

        complaintTableBody.appendChild(complaintRow);
    }
}

document.addEventListener("click", async event =>{
    const element = event.target;
    const complaintId = element.dataset.complaintID;
    const complaintPriority = document.getElementById("priorityId" + complaintId);
    const complaintMeeting = document.getElementById("complaintMeeting" + complaintId);
    if (complaintMeeting.value === "NONE")
    {
        complaintMeeting.value = -1;
    }
    //send the http with the data given
    if(element.id == "updateComplaint"){
        const updatePriority = await fetch(`http://localhost:8080/complaints/${complaintId}/${complaintPriority.value}`, {
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        }
    });
    const updateMeeting = await fetch(`http://localhost:8080/complaints/${complaintId}/meetings/${complaintMeeting.value}`, {
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        }
    });
    if(updatePriority.status === 200 && updateMeeting.status == 200){
        alert("Successfully updated");
        location.reload();
    }
    else
    {
        alert("Something went wrong updating that complaint.");
    }
    }
    if(element.id == "updateAll"){
        alert("Updating all complaints. This can take a while... Press OK")
        const complaints = await getComplaints();
        const meetingIdValue = document.getElementById("updateAllMeetingId").value;
        const prioritySelector = document.getElementById("updateAllPriority").value;;
        for(const c of complaints){
            if(meetingIdValue > 0)
            {
                if(c.id === meetingIdValue){
                    updateComplaints(c.id, meetingIdValue, prioritySelector);
                }
            }
        }
        alert("All complaints have been updated.");
    }

    async function updateComplaints(complaintId, meetingId, complaintPriority)
    {
        if(element.id == "updateComplaint"){
            const updatePriority = await fetch(`http://localhost:8080/complaints/${complaintId}/${complaintPriority}`, {
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            }
        });
        const updateMeeting = await fetch(`http://localhost:8080/complaints/${complaintId}/meetings/${meetingId}`, {
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            }
        });
    }

}});

renderComplaintsTable();