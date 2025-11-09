
# FileAttachmentResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`fileName` | string
`contentType` | string
`size` | number
`url` | string
`entityType` | string
`entityId` | string
`uploadPurpose` | string

## Example

```typescript
import type { FileAttachmentResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "fileName": null,
  "contentType": null,
  "size": null,
  "url": null,
  "entityType": null,
  "entityId": null,
  "uploadPurpose": null,
} satisfies FileAttachmentResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as FileAttachmentResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


