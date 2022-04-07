/**Inspiration from Web Dev Simplified**/
const draggable = document.querySelectorAll('.board-note')
const containers = document.querySelectorAll('.board-background')

draggable.forEach(draggable => {
    draggable.addEventListener('dragstart', ()=> {
        draggable.classList.add('dragging')
        console.log('drag start')
    })
    draggable.addEventListener('dragend', () => {
        draggable.classList.remove('dragging')
        console.log('drag end')
    })
})

containers.forEach(containers => {
    containers.addEventListener('dragover', e => {
        e.preventDefault()
        const afterElement = getDragPosition(containers,  e.clientY, e.clientX)
        const draggable = document.querySelector('.dragging')
        containers.appendChild(draggable)
    })
})

function getDragPosition(container, y, x) {
    const draggableElements = [...container.querySelectorAll('.draggable:not(.dragging)')]

    draggableElements.reduce((closest, child) => {
        const box = child.getBoundingClientRect()
    },{offset: Number.POSITIVE_INFINITY})
}

