import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-single-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.scss'],
})
export class SingleProductComponent {
  @Input() product: any = {}; // Accept product data from parent component

  constructor() {}

  ngOnInit(): void {}

  getImageUrl(folderName: string, imageName: string): string {
  // Log the inputs and URL to ensure they are as expected
  console.log('Folder Name:', folderName);
  console.log('Image Name:', imageName);
  const encodedFolderName = encodeURIComponent(folderName);
  const encodedImageName = encodeURIComponent(imageName);
  const url = `assets/img/${encodedFolderName}/${encodedImageName}`;
  console.log('Generated Image URL:', url);
  return url;
}


  getFirstImage(): { url: string, name: string } {
    if (this.product.images && this.product.images.length > 0) {
      const firstImage = this.product.images[0];
      const imageUrl = this.getImageUrl(this.product.name, firstImage);
      console.log('Image URL:', imageUrl); // Log URL to verify
      return { url: imageUrl, name: firstImage };
    }
    return { url: 'assets/img/placeholder-image.jpg', name: 'No image available' };
  }
  
}
