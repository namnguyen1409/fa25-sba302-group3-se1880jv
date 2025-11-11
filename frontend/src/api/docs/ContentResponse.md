
# ContentResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`createdBy` | string
`createdDate` | Date
`lastModifiedBy` | string
`lastModifiedDate` | Date
`title` | string
`slug` | string
`body` | string
`excerpt` | string
`coverImageUrl` | string
`status` | string
`publishedAt` | Date
`tags` | Set&lt;string&gt;
`author` | [StaffResponse](StaffResponse.md)

## Example

```typescript
import type { ContentResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "createdBy": null,
  "createdDate": null,
  "lastModifiedBy": null,
  "lastModifiedDate": null,
  "title": null,
  "slug": null,
  "body": null,
  "excerpt": null,
  "coverImageUrl": null,
  "status": null,
  "publishedAt": null,
  "tags": null,
  "author": null,
} satisfies ContentResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ContentResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


