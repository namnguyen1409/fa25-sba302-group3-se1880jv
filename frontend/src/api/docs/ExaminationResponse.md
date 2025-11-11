# ExaminationResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**staff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**type** | **string** |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**symptom** | **string** |  | [optional] [default to undefined]
**diagnosisSummary** | **string** |  | [optional] [default to undefined]
**examinationDate** | **string** |  | [optional] [default to undefined]
**prescription** | [**PrescriptionResponse**](PrescriptionResponse.md) |  | [optional] [default to undefined]
**serviceOrders** | [**Set&lt;ServiceOrderResponse&gt;**](ServiceOrderResponse.md) |  | [optional] [default to undefined]
**vitalSigns** | [**Set&lt;VitalSignResponse&gt;**](VitalSignResponse.md) |  | [optional] [default to undefined]
**diagnoses** | [**Set&lt;DiagnosisResponse&gt;**](DiagnosisResponse.md) |  | [optional] [default to undefined]
**labOrders** | [**Set&lt;LabOrderResponse&gt;**](LabOrderResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { ExaminationResponse } from './api';

const instance: ExaminationResponse = {
    id,
    patient,
    staff,
    type,
    status,
    symptom,
    diagnosisSummary,
    examinationDate,
    prescription,
    serviceOrders,
    vitalSigns,
    diagnoses,
    labOrders,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
