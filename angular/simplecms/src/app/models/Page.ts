export interface Page {
  parentId: number;
  title: string;
  slug: string;
  content: string;
  isVisible: number;
  orderIndex: number;
  metaTitle?: string;
  metaDescription?: string;
  createdAt?: string;
  updatedAt?: string;
}