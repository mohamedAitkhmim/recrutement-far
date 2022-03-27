
function onNoteUniqueChange()
{
	let note=document.getElementById('note');
	let no=document.getElementById('noteoption');
	if(noteUnique.checked)
	{
		note.style.display = "block";
		no.style.display = "none";
	}
	else
	{
		note.style.display = "none";
		no.style.display = "block";
	}
}
