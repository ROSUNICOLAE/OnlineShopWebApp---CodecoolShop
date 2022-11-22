

export const productCard = (product) => {
    return `<div class="card">
                <div class="product-img-holder">
                    <img class="product-img" src='${product.img}' alt=""/>
                </div>
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text card-description">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text card-price child">
                        <p class="lead"> ${product.price}</p>
                    </div>
                    <div class="child">
                    <a data-id="${product.id}" class="btn btn-add add" id="add-to-cart">Add to cart</a>
                    </div>
                </div>
            </div>`
}