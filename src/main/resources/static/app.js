const serverUrl = "http://localhost:8080";
const uploadPage = document.getElementById('uploadPage');
const galleryPage = document.getElementById('galleryPage');
const uploadBtn = document.getElementById('uploadBtn');
const galleryBtn = document.getElementById('galleryBtn');
const uploadForm = document.getElementById('uploadForm');
const fileInput = document.getElementById('fileInput');
const uploadMessage = document.getElementById('uploadMessage');
const galleryContainer = document.getElementById('gallery');

function init() {
    uploadBtn.addEventListener('click', () => showPage('upload'));
    galleryBtn.addEventListener('click', () => showPage('gallery'));
    uploadForm.addEventListener('submit', handleUpload);
}

function showPage(page) {
    uploadPage.style.display = page === 'upload' ? 'block' : 'none';
    galleryPage.style.display = page === 'gallery' ? 'block' : 'none';
    if (page === 'gallery') loadGallery();
}

async function handleUpload(e) {
    e.preventDefault();
    const file = fileInput.files[0];
    if (!file) return showError("Please select a file first");

    const formData = new FormData();
    formData.append("file", file);
    const response = await fetch(`${serverUrl}/api/photo`, { method: "POST", body: formData });
    if (!response.ok) throw new Error("Upload failed");
    showSuccess("Photo successfully uploaded!");
    fileInput.value = "";
}

async function loadGallery() {

    const res = await fetch(`${serverUrl}/api/photos`);
    if (!res.ok) throw new Error("Failed to fetch photos");
    const photos = await res.json();
    displayGallery(photos);
}


function displayGallery(photos) {
    galleryContainer.innerHTML = '';
    photos.forEach(photo => {
        const img = document.createElement('img');
        img.src = `${serverUrl}/api/photo/${photo.id}`;
        img.alt = "Uploaded photo";
        img.className = "gallery-photo";
        galleryContainer.appendChild(img);
    });
}

function showSuccess(msg) {
    uploadMessage.textContent = msg;
    uploadMessage.style.color = "green";
}

function showError(msg) {
    uploadMessage.textContent = msg;
    uploadMessage.style.color = "red";
}

init();
