function checkAnswer1(){
    const form = document.querySelector('#Exercise1-form');
    const feedback = document.querySelector('#feedback');

      form.addEventListener('submit', function(event) {
        event.preventDefault();

        const answers = ['The house is empty', '5 < 4', 'Maths is interesting']; // correct answers
        const userAnswers = [];
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        for (let i = 0; i < checkboxes.length; i++) {
          userAnswers.push(checkboxes[i].value);
        }

        let correctCount = 0;
        let incorrectCount = 0;
        let incorrectAnswers = [];

        userAnswers.forEach(answer => {
          if (answers.includes(answer)) {
            correctCount++;
          } else {
            incorrectCount++;
            incorrectAnswers.push(answer);
          }
        });

        let feedbackHTML = `You got ${correctCount} out of ${answers.length} correct.`
        if (incorrectCount > 0) {
          feedbackHTML += `<br>Incorrect answers: ${incorrectAnswers.join(', ')}.`;
        }

        feedback.innerHTML = feedbackHTML;
      });
}

    function addNavbar() {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("navbar").innerHTML = this.responseText;
        }
      };
      xhttp.open("GET", "navbar.html", true);
      xhttp.send();
    }

    function darkLight(){
        const body = document.body;
        const changeBtn = document.querySelector('.change');
          if (body.classList.contains('dark')) {
            body.classList.remove('dark');
            changeBtn.innerText = 'Light';
            sessionStorage.setItem('theme', 'light');
          } else {
            body.classList.add('dark');
            changeBtn.innerText = 'Dark';
            sessionStorage.setItem('theme', 'dark');
          };
    }
    // Check for saved theme in localStorage and set it on page load
    if (sessionStorage.getItem('theme') === 'dark') {
      darkLight();
    }
function checkAnswer2(){
const form = document.querySelector('#Exercise2-form');
const feedback = document.querySelector('#feedback2');
  form.addEventListener('submit', function(event) {
   event.preventDefault();
   let feedbackHTML = `<div class="row">
                         <div class="col">
                           <table class="table table-dark table-striped table-hover table-bordered text-white">
                             <thead>
                               <tr class= "tr text-center">
                                 <th scope="col">P</th>
                                 <th scope="col">Q</th>
                                 <th scope="col">¬P</th>
                                 <th scope="col">¬P | Q</th>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               </thead>
                           </table>
                           </div>
                           <div class="col">
                               <table class="table table-dark table-striped table-hover table-bordered text-white">
                                   <thead>
                                   <tr class= "tr text-center">
                                       <th scope="col">P</th>
                                       <th scope="col">Q</th>
                                       <th scope="col">¬P</th>
                                       <th scope="col">¬Q</th>
                                       <th scope="col">¬(¬P & ¬Q)</th>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                   </tr>
                                   </thead>
                               </table>
                           </div>
                       </div>
                       <div class="row">
                          <div class="col">
                              <table class="table table-dark table-striped table-hover table-bordered text-white">
                                  <thead>
                                  <tr class= "tr text-center">
                                      <th scope="col">P</th>
                                      <th scope="col">Q</th>
                                      <th scope="col">¬(P => Q)</th>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  </thead>
                              </table>
                          </div>
                          <div class="col">
                              <table class="table table-dark table-striped table-hover table-bordered text-white">
                                  <thead>
                                  <tr class= "tr text-center">
                                      <th scope="col">P</th>
                                      <th scope="col">Q</th>
                                      <th scope="col">¬P</th>
                                      <th scope="col">(P & Q) <=> ¬P</th>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  <tr class= "text-center">
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-success">True</span>
                                      </td>
                                      <td class="td">
                                          <span class="badge bg-danger">False</span>
                                      </td>
                                  </tr>
                                  </thead>
                              </table>
                          </div>
                      </div>`
   feedback.innerHTML = feedbackHTML;
  });
}
function checkAnswer3(){
const form = document.querySelector('#Exercise3-form');
const feedback = document.querySelector('#feedback3');
  form.addEventListener('submit', function(event) {
   event.preventDefault();
   let feedbackHTML = `<div class="row">
                         <div class="col">
                           <table class="table table-dark table-striped table-hover table-bordered text-white">
                             <thead>
                               <tr class= "tr text-center">
                                 <th scope="col">P</th>
                                 <th scope="col">Q</th>
                                 <th scope="col">¬(P => Q)</th>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                               </tr>
                               </thead>
                           </table>
                           </div>
                           <div class="col">
                               <table class="table table-dark table-striped table-hover table-bordered text-white">
                                   <thead>
                                   <tr class= "tr text-center">
                                       <th scope="col">P</th>
                                       <th scope="col">Q</th>
                                       <th scope="col">¬Q</th>
                                       <th scope="col">(P & ¬Q)</th>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                   </tr>
                                   <tr class= "text-center">
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-success">True</span>
                                       </td>
                                       <td class="td">
                                           <span class="badge bg-danger">False</span>
                                       </td>
                                   </tr>
                                   </thead>
                               </table>
                           </div>
                       </div>`
   feedback.innerHTML = feedbackHTML;
  });
}
function checkAnswer4(){
const form = document.querySelector('#Exercise4-form');
const feedback = document.querySelector('#feedback4');
  form.addEventListener('submit', function(event) {
   event.preventDefault();
   let feedbackHTML = `<div class="row">
                           <table class="table table-dark table-striped table-hover table-bordered text-white">
                             <thead>
                               <tr class= "tr text-center">
                                 <th scope="col">P</th>
                                 <th scope="col">Q</th>
                                 <th scope="col">R</th>
                                 <th scope="col">Q | R</th>
                                 <th scope="col">P & (Q | R)</th>
                                 <th scope="col">P & Q</th>
                                 <th scope="col">P & R</th>
                                 <th scope="col">(P & Q) | (P & R)</th>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               </thead>
                           </table>
                           <div class="row">
                               <table class="table table-dark table-striped table-hover table-bordered text-white">
                             <thead>
                               <tr class= "tr text-center">
                                 <th scope="col">P</th>
                                 <th scope="col">Q</th>
                                 <th scope="col">R</th>
                                 <th scope="col">Q & R</th>
                                 <th scope="col">P & (Q & R)</th>
                                 <th scope="col">P & Q</th>
                                 <th scope="col">(P & Q) & R</th>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-success">True</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               <tr class= "text-center">
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                                 <td class="td">
                                   <span class="badge bg-danger">False</span>
                                 </td>
                               </tr>
                               </thead>
                               </table>
                           </div>
                       </div>`
   feedback.innerHTML = feedbackHTML;
  });
}
function checkAnswer5(){
const form = document.querySelector('#Exercise5-form');
const feedback = document.querySelector('#feedback5');
  form.addEventListener('submit', function(event) {
   event.preventDefault();
   let feedbackHTML = `<div>
                           <table class="table table-dark table-striped table-hover table-bordered text-white">
                               <thead>
                               <tr class= "tr text-center">
                                   <th scope="col">C</th>
                                   <th scope="col">V</th>
                                   <th scope="col">C => V</th>
                                   <th scope="col">(C => V) & V</th>
                                   <th scope="col">[(C => V) & V] => C</th>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                               </tr>
                               <tr class= "text-center">
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-danger">False</span>
                                   </td>
                                   <td class="td">
                                       <span class="badge bg-success">True</span>
                                   </td>
                               </tr>
                               </thead>
                           </table>
                           <p>We can see from the column that the proposition is not a tautology, and so the argument is not valid; it is possible for an incorrect program to pass the validation test.</p>
                       </div>`
   feedback.innerHTML = feedbackHTML;
  });
}
function checkAnswer6(){
const form = document.querySelector('#Exercise6-form');
const feedback = document.querySelector('#feedback6');
  form.addEventListener('submit', function(event) {
   event.preventDefault();
   let feedbackHTML = `<p style="text-align: center;"><b>Given S | H, ¬H prove S</b></p>
                       <div class="row">
                           <div class="col">
                               <ol style="text-align: left;">
                                   <li>S <b style="color: DodgerBlue">|</b> H</li>
                                   <li>H <b style="color: DodgerBlue">|</b> S</li>
                                   <li>¬¬H <b style="color: DodgerBlue">|</b> S</li>
                                   <li>¬H <b style="color: DodgerBlue">=></b> S</li>
                                   <li>¬H</li>
                                   <li>S</li>
                               </ol>
                           </div>
                           <div class="col">
                               <ul style="text-align: left;">
                                   <li>Hypothesis</li>
                                   <li>1, (<b style="color: DodgerBlue">|</b> - Comm)</li>
                                   <li>DN</li>
                                   <li>3, (<b style="color: DodgerBlue">=></b> - Equiv)</li>
                                   <li>Hypothesis</li>
                                   <li>4,5, (<b style="color: DodgerBlue">=></b> - Elim)</li>
                               </ul>
                           </div>
                       </div>`
   feedback.innerHTML = feedbackHTML;
  });
}
