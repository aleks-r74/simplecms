import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Page } from '../models/Page';
import { PageEventService } from '../services/page-event-service';

@Component({
  selector: 'app-page-edit',
  imports: [ReactiveFormsModule],
  templateUrl: './page-edit.html',
})
export class PageEdit  implements OnInit {
  @Input() pageData?: Page;

  pageForm!: FormGroup;

  constructor(private fb: FormBuilder, private peService: PageEventService) {}

  ngOnInit(): void {
    this.pageForm = this.fb.group({
      parentId: [this.pageData?.parentId ?? 0],
      title: [this.pageData?.title ?? '', Validators.required],
      slug: [this.pageData?.slug ?? '', Validators.required],
      content: [this.pageData?.content ?? '', Validators.required],
      isVisible: [this.pageData?.isVisible ?? 1],
      orderIndex: [this.pageData?.orderIndex ?? 0],
      metaTitle: [this.pageData?.metaTitle ?? ''],
      metaDescription: [this.pageData?.metaDescription ?? ''],
    });
  }

  onSubmit() {
    if (this.pageForm.valid) {
      this.peService.emitSave(this.pageForm.value);
    } else {
      this.pageForm.markAllAsTouched();
    }
  }
}