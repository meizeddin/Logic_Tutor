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